package com.question.engine.factory.impl.simple.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;

public class ExcelDataHandler extends DataHandler  {

	static Logger logger = Logger.getLogger(ExcelDataHandler.class);
	
	@Override
	public boolean saveSessionData(ContextDAO ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionDAO getSessionData(ContextDAO application)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveData(ContextDAO ctx, SessionDAO dao)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object loadData(Object obj) {

		logger.info("Begin load...");
		
		List<QuestionDAO> questions = new ArrayList<QuestionDAO>();
		QuestionDAO q = null;	
		
		try {
			FileInputStream file = null;
			XSSFWorkbook workbook = null;
			if(obj instanceof InputStream) {
				workbook = new XSSFWorkbook((InputStream)obj);
			} else {
				file = new FileInputStream(new File(
						obj.toString()));
				workbook = new XSSFWorkbook(file);
				
			}

			// Create Workbook instance holding reference to .xlsx file
			//XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			int questionCounter = 1;
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				logger.info("-----------------");
				List<Object> items = new ArrayList<Object>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						logger.debug(cell.getNumericCellValue() + "");
						items.add(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						logger.debug(cell.getStringCellValue() + "");
						items.add(cell.getStringCellValue());
						break;
					}
					logger.info("\t");
				}
				if(items.size() == 0) {
					continue;
				}
				q = new QuestionDAO();
				q.setQuestionNumber(questionCounter);
				q.setCorrectAnswerCount(0);
				q.setExplanation(items.get(0).toString());
				q.setQuestion(items.get(1).toString());
				q.setAnswer(items.get(2).toString());
				q.setQuestionType( items.get(2).toString().contains(",") ? "multi" : "single");
				if(items.get(2).toString().contains("=")) {
					q.setQuestionType("match-term");	
				}
				Map<String,String> m1 = new HashMap<String,String>();
				//this can through an error if user put 6 answers or more. will throw business logic in future.
				String[] letters = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","o","p","q","r","s","t","u","v","w","x","y","z"};
				for(int i=3;i<items.size();i++) {
					m1.put((letters[i-3]),items.get(i).toString());	
				}
				q.setSelections(m1);
				questions.add(q);
				questionCounter++;
				
				logger.info("");
			}
			if(obj instanceof String) file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

}

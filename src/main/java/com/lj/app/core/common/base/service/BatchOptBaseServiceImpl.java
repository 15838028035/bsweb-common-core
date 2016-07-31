package com.lj.app.core.common.base.service;

import jxl.Cell;
import jxl.Sheet;

import org.springframework.stereotype.Service;


@Service("batchOptBaseService")
public abstract class BatchOptBaseServiceImpl extends BaseServiceImpl implements BatchOptBaseService {

	/**
	 * 校验单元格行 在列范围j内，若出现不为空的cell，则认为该行为有效行;无效行跳过执行
	 * 
	 * @param sheet
	 * @param i
	 * @param column
	 * @param excelFieldSize
	 * @return
	 */
	public boolean validateRow(Sheet sheet, int i, int column,
			int excelFieldSize) {

		for (int j = 0; j < column && j < excelFieldSize; j++) {
			Cell cell = sheet.getCell(j, i);
			String value = cell.getContents() != null ? cell.getContents()
					.trim() : "";
			if (!value.equals(""))
				return true;
		}
		return false;
	}
		
}

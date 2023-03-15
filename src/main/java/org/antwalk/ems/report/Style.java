package org.antwalk.ems.report;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class Style {
    
    public CellStyle descriptorFieldStyle(XSSFWorkbook workbook){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");
        style.setFont(font);

        style.setAlignment(HorizontalAlignment.CENTER); 
        return style;       
    }
}

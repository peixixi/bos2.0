package cn.itcast.action.base;

import cn.itcast.domain.Area;
import cn.itcast.service.base.AreaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")

public class AreaAction extends ActionSupport {
    @Autowired
    private AreaService areaServiceImpl;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }


    @Action(value="area_batchImport")
    public void batchImport() throws IOException, InvalidFormatException {

        Workbook workbook = new WorkbookFactory().create(new FileInputStream(file));
        List<Area> areas=new ArrayList<Area>();
        Sheet sheet =workbook.getSheetAt(0);
        for(Row row:sheet){
            
            if(row.getRowNum()==0){
                continue;
            }
            if(row.getCell(0)==null || StringUtils.isBlank(row.getCell(0).getStringCellValue())){
                    continue;
            }
            Area area=new Area();
            area.setId(row.getCell(0).getStringCellValue());
            area.setProvince(row.getCell(1).getStringCellValue());
            area.setCity(row.getCell(2).getStringCellValue());
            area.setDistrict(row.getCell(3).getStringCellValue());
            area.setPostcode(row.getCell(4).getStringCellValue());
            areas.add(area);



            }
        areaServiceImpl.saveBatch(areas);
        }



}

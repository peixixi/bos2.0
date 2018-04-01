package cn.itcast.action.base;
import cn.itcast.domain.Standard;
import cn.itcast.service.base.StandardService;
import cn.itcast.service.base.impl.StandardServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Actions
@Namespace("/")
@ParentPackage("json-default")
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {
    @Autowired
    private StandardService standardService;
    private Integer page;
    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    private Standard standard =new Standard();
    @Override
    public Standard getModel() {
        return standard;
    }
    @Action(value="standard_save",results = {@Result(name=SUCCESS,type="redirect",location = "./pages/base/standard.html")})
    public String save(){
        standardService.save(standard);
        System.out.println("成功");
        return SUCCESS;
    }
    @Action(value="standard_findAll",results = {@Result(name=SUCCESS,type="json")})
    public String findAll(){
        Pageable pageable=  new PageRequest(page-1,rows);
       Page<Standard> list=standardService.findAll(pageable);
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("total",list.getTotalElements());
       map.put("rows",list.getContent());
        ActionContext.getContext().getValueStack().push(map);
        return SUCCESS;
    }
    @Action(value="standard_courierFindAll",results={@Result(name="success",type="json")})
    public String courierFindAll(){
        List<Standard> standards = standardService.findAll();
        ActionContext.getContext().getValueStack().push(standards);
        System.out.println(standards);

        return SUCCESS;

    }
}

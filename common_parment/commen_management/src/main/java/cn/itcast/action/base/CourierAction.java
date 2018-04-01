package cn.itcast.action.base;

import cn.itcast.domain.Courier;
import cn.itcast.domain.Standard;
import cn.itcast.service.base.CourierService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.*;
import java.util.*;

@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope(value = "prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {
    private Courier courier=new Courier();
    @Autowired
    private CourierService courierServiceImpl;
    private String courierNum;
    private String  company;
    private String type;
    private Standard standard;
    private int page;
    private int rows;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getCourierNum() {
        return courierNum;
    }

    public void setCourierNum(String courierNum) {
        this.courierNum = courierNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    @Override
    public Courier getModel() {
        return courier;
    }
    @Action(value="courier_save",results={@Result(name="success",type="redirect",location = "./pages/base/courier.html")})
    public String save(){
        courierServiceImpl.save(courier);
        return SUCCESS;
    }
    @Action(value="courier_pageQuery",results={@Result(name="success",type="json")})
    public String pageQuery(){
        Specification<Courier> specification=new Specification(){
            List<Predicate> ps=new ArrayList<Predicate>();

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                if(StringUtils.isNotBlank(courier.getCompany())){
                    Predicate p1=cb.like(root.get("company").as(String.class),"%"+courier.getCompany()+"%");
                    ps.add(p1);
                }
                if(StringUtils.isNotBlank(courier.getCourierNum())){
                    Predicate p2=cb.equal(root.get("courierNum").as(String.class),courier.getCourierNum());
                    ps.add(p2);
                }
                if(StringUtils.isNotBlank(courier.getType())){
                    Predicate p3=cb.like(root.get("type").as(String.class),"%"+courier.getType()+"%");
                    ps.add(p3);
                }
               Join<Object,Object> standardRoot=root.join("standard",JoinType.INNER);
                if(courier.getStandard()!=null){
                    Predicate p4=cb.like(standardRoot.get("name").as(String.class),"%"+courier.getStandard().getName()+"%");
                    ps.add(p4);
                }
                return cb.and( ps.toArray(new Predicate[0]));
            }

        };
        Pageable pageable=new PageRequest(page-1,rows);
        Page<Courier> page=courierServiceImpl.findPageData(specification,pageable);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",page.getTotalElements());
        map.put("rows",page.getContent());
        ActionContext.getContext().getValueStack().push(map);


        return SUCCESS;
    }
    @Action(value="courier_delBatch" ,results = {@Result(name="success",type="redirect",location="./pages/base/courier.html")})
    public String delBatch(){
        String[] idd=ids.split(",");
        courierServiceImpl.delCourier(idd);
        return SUCCESS;

    }
}


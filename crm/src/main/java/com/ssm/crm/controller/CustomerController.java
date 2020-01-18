package com.ssm.crm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.crm.constart.PagenationConstart;
import com.ssm.crm.pojo.Customer;
import com.ssm.crm.pojo.Dict;
import com.ssm.crm.service.CustomerService;
import com.ssm.crm.service.DictService;
import com.ssm.crm.vo.CustomerSearchVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 客户关系
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Value("${dict.type.source}")
    private String dictTypeSource;
    @Value("${dict.type.industry}")
    private String dictTypeIndustry;
    @Value("${dict.type.level}")
    private String dictTypeLevel;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DictService dictService;

    /**
     * 下拉选框数据加载
     * @param model
     */
    @ModelAttribute
    public void loadCustomer(Model model){
        // 客户列表
        List<Dict> sourceList = dictService.getDictListByTypeCode(dictTypeSource);
        // 客户行业
        List<Dict> industryList = dictService.getDictListByTypeCode(dictTypeIndustry);
        // 客户级别
        List<Dict> levelList = dictService.getDictListByTypeCode(dictTypeLevel);
        model.addAttribute("fromType",sourceList);
        model.addAttribute("industryType",industryList);
        model.addAttribute("levelType",levelList);
    }

    @RequestMapping("/list")
    public String showList(CustomerSearchVo customerVo, Integer page, Integer size, Model model) throws UnsupportedEncodingException {
        // iso8859-1
        //处理 get 参数 中文乱码
        String customerName = customerVo.getCustomerName();
        if (StringUtils.isNotBlank(customerName)){
            customerName = new String(customerName.getBytes("iso8859-1"),"utf-8");
            customerVo.setCustomerName(customerName);
        }
        /* 放到上面加载
        // 客户列表
        List<Dict> sourceList = dictService.getDictListByTypeCode(dictTypeSource);
        // 客户行业
        List<Dict> industryList = dictService.getDictListByTypeCode(dictTypeIndustry);
        // 客户级别
        List<Dict> levelList = dictService.getDictListByTypeCode(dictTypeLevel);
        model.addAttribute("fromType",sourceList);
        model.addAttribute("industryType",industryList);
        model.addAttribute("levelType",levelList);
        */

        if (ObjectUtils.isEmpty(page)){
            page = PagenationConstart.PAGE_NUM;
        }
        if (ObjectUtils.isEmpty(size)){
            size = PagenationConstart.PAGE_SIZE;
        }
        PageInfo<Customer> customerPageInfo = customerService.getCustomerList(customerVo, page, size);
        model.addAttribute("paging",customerPageInfo);

        model.addAttribute("custName",customerVo.getCustomerName());
        model.addAttribute("custSource",customerVo.getCustSource());
        model.addAttribute("custIndustry",customerVo.getCustIndustory());
        model.addAttribute("custLevel",customerVo.getCustLevel());
        return "customer";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Customer edit(long id){
        return customerService.getCustomerById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Customer customer){
        customerService.updateCustomerById(customer);
        return "ok";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id){
        customerService.delete(id);
        return "ok";
    }
}

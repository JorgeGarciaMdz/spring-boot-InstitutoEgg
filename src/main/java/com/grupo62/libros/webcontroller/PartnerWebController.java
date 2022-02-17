package com.grupo62.libros.webcontroller;

import java.util.Date;

import javax.validation.Valid;

import com.grupo62.libros.entity.Partner;
import com.grupo62.libros.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerWebController {

    @Autowired
    private PartnerService partnerService;
    
    @GetMapping
    public String showPartnerList(Model model){
        model.addAttribute("partners", partnerService.findAll());
        return "/partner/partner";
    }

    /* --------- create Cliente ------ */
    @GetMapping("/new")
    public String showSignUpForm(Partner partner){
        return "/partner/form-partner";
    }

    @PostMapping("/partner-create")
    public String createPartner(@Valid Partner partner, BindingResult result, Model model){
        if( result.hasErrors())
            return "/partner/form-partner";
        partner.setCreatedAt(new Date());
        partner.setUpdatedAt(new Date());
        partnerService.save(partner);
        return "redirect:/partner";
    }

    /* ------- end create Cliente ------- */

    /* ------- update Cliente ------- */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, Model model){
        Partner partner = partnerService.findById(id);
        if( partner == null ){
            model.addAttribute("partners", partnerService.findAll());
            return "redirect:/partner";
        }
        model.addAttribute("partner", partner);
        return "/partner/update-partner";
    }

    @PostMapping("/partner-update/{id}")
    public String updatePartner(@PathVariable(name = "id") Long id, @Valid Partner partner, BindingResult result, Model model){
        if( result.hasErrors()){
            partner.setId(id);
            return "/partner/update-partner";
        }
        partner.setUpdatedAt(new Date());
        partnerService.save(partner);
        return "redirect:/partner";
    }
    /* ------- end update Cliente ------- */

    @GetMapping("/delete/{id}")
    public String deletePartner(@PathVariable(name = "id") Long id, Model model){
        Partner partner = partnerService.findById(id);
        if( partner != null )
        partnerService.delete(partner);

        return "redirect:/partner";
    }
}

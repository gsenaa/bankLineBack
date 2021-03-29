package bankline.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankline.dtos.PlanAccountDto;
import bankline.form.PlanAccountForm;
import bankline.model.PlanAccount;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;
import bankline.service.PlanAccountService;

@RestController
@RequestMapping("/lancamento/plan-account")
public class PlanAccountController {

    @Autowired
    private PlanAccountRepository planAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping    
    private ResponseEntity<String> criarPlan(@Valid @RequestBody PlanAccountForm pAccountForm) {
        
        PlanAccountService service = new PlanAccountService();

        PlanAccount plansList = service.planAccountPostService(pAccountForm, planAccountRepository, userRepository);

        if(plansList == null) {
            return ResponseEntity.badRequest().body("Tipo de movimento inválido");
        }

        planAccountRepository.save(plansList);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private List<PlanAccountDto> getPlans(String login) {

        List<PlanAccount> planList = planAccountRepository.findAllByUserLogin(login);
 
        return PlanAccountDto.converter(planList);
    }
}

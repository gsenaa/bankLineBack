package bankline.service;

import javax.validation.Valid;

import bankline.form.PlanAccountForm;
import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;
import bankline.model.User;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;

public class PlanAccountService {

	public PlanAccount planAccountPostService(@Valid PlanAccountForm pAccountForm,
			PlanAccountRepository planAccountRepository, UserRepository userRepository) {
		
                User user = userRepository.findByLogin(pAccountForm.getLogin());
                
                PlanAccountType tipo = PlanAccountType.CHARGE;
        
                switch (pAccountForm.getTipoMovimento()) {
                    case "R":
                        tipo = PlanAccountType.REVENUE;
                        break;
                
                    case "T":
                        tipo = PlanAccountType.TRANSFER;
                        break;
                    
                    case "C":
                        tipo = PlanAccountType.CHARGE;
                        break;

                    default: 
                        return null;
                }
                
                
                System.out.println(tipo);
                
                PlanAccount planAccount = new PlanAccount(pAccountForm, tipo, user);
        
                return planAccount;
	}
    
}

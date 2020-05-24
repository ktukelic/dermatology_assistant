package sbnz.blisskin.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.model.User;
import sbnz.blisskin.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SessionService {

    private final KieBase kieBase;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public SessionService(KieBase kieBase, IngredientRepository ingredientRepository) {
        this.kieBase = kieBase;
        this.ingredientRepository = ingredientRepository;
    }

    public void initializeSession(User user) {
        KieSession kieSession = kieBase.newKieSession();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        final Authentication authentication = new PreAuthenticatedAuthenticationToken(kieSession, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void insertInitialFacts() {
        KieSession kieSession = getKieSession();
        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.stream().forEach(kieSession::insert);
    }

    public KieSession getKieSession() {
        return (KieSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

package com.example.demo.init;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RoleInit implements CommandLineRunner {

    private final RoleRepository repository;

    public RoleInit(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        if (repository.count() == 0) {
            log.info("Loading roles");
            Role user = new Role(RoleName.ROLE_USER);
            Role admin = new Role(RoleName.ROLE_ADMIN);

            repository.save(user);
            repository.save(admin);
        }
    }
}

package com.andyg.SpringSecurityApp;

import com.andyg.SpringSecurityApp.persistence.entity.PermissionEntity;
import com.andyg.SpringSecurityApp.persistence.entity.RoleEntity;
import com.andyg.SpringSecurityApp.persistence.entity.RoleEnum;
import com.andyg.SpringSecurityApp.persistence.entity.UserEntity;
import com.andyg.SpringSecurityApp.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//Create permissionS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//Create Role
			RoleEntity adminRole = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity userRole = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity invitedRole = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity developerRole = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission,
							refactorPermission))
					.build();

			//Create users
			UserEntity userAndy = UserEntity.builder()
					.username("Andy")
					.password("$2a$10$xWrtkeC7vyuT9CLlmpvYbuSpsViMthLrEagj4EZZGURgv8ggJm9i6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(developerRole))
					.build();

			UserEntity userCristian = UserEntity.builder()
					.username("Cristian")
					.password("$2a$10$xWrtkeC7vyuT9CLlmpvYbuSpsViMthLrEagj4EZZGURgv8ggJm9i6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(adminRole))
					.build();

			UserEntity userLeo = UserEntity.builder()
					.username("Leo")
					.password("$2a$10$xWrtkeC7vyuT9CLlmpvYbuSpsViMthLrEagj4EZZGURgv8ggJm9i6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(adminRole))
					.build();

			UserEntity userJose = UserEntity.builder()
					.username("Pablo")
					.password("$2a$10$xWrtkeC7vyuT9CLlmpvYbuSpsViMthLrEagj4EZZGURgv8ggJm9i6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(invitedRole))
					.build();

			UserEntity userDavid = UserEntity.builder()
					.username("David")
					.password("$2a$10$xWrtkeC7vyuT9CLlmpvYbuSpsViMthLrEagj4EZZGURgv8ggJm9i6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(invitedRole))
					.build();

			userRepository.saveAll(List.of(userAndy, userLeo, userCristian, userJose, userDavid));
		};

	}

}

package com.back.api.repository;

import com.back.api.domain.User;
import lombok.NonNull;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void crud() {
        System.out.println("<< save");
        User user = new User();
        user.setEmail("reolino@gmail.com");
        user.setName("reolino");
        user.setPassword("123456");
        user.setBirth("20200101");
        user.setIndate(LocalDateTime.now());
        user.setLdate(LocalDateTime.now());
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("reolino2@gmail.com");
        user2.setName("reolino2");
        user2.setPassword("123456789");
        user2.setBirth("20201231");
        user2.setIndate(LocalDateTime.now());
        user2.setLdate(LocalDateTime.now());
        userRepository.save(user2);

        System.out.println("<< findAllById");
        List<User> users2 = userRepository.findAllById(Lists.newArrayList(2L));
        users2.forEach(System.out::println);

        System.out.println("<< save all");
        User user3 = new User("reolino3", "reolino3@gmail.com","123456789", "01011112222");
        User user4 = new User("reolino4", "reolino4@gmail.com","123456789", "01011113333");
        userRepository.saveAll(Lists.newArrayList(user3, user4));

        System.out.println("<< getOne");
        User user5 = userRepository.getOne(1L); //transactional 필요
        System.out.println(user5);

        System.out.println("<< findById");
        User user6 = userRepository.findById(3L).orElse(null);
        System.out.println(user6);

        userRepository.flush();

        System.out.println("<< count");
        long count = userRepository.count();
        System.out.println(count);

        System.out.println("<< existsById");
        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);

        System.out.println("<< delete");
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        boolean exists2 = userRepository.existsById(1L);
        System.out.println(exists2);

        System.out.println("<< deleteById");
        userRepository.deleteById(2L);
        boolean exists3 = userRepository.existsById(2L);
        System.out.println(exists3);

        System.out.println("<< findAll");
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        users.forEach(System.out::println);

        System.out.println("<< deleteInBatch");
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(3L)));
        boolean exists4 = userRepository.existsById(3L);
        System.out.println(exists4);

        System.out.println("<< deleteAll");
        userRepository.deleteAll();

        System.out.println("<< deleteAllInBatch");
        userRepository.deleteAllInBatch();

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud2() {
        System.out.println("<< save");
        User user = new User();
        user.setEmail("reolino@gmail.com");
        user.setName("reolino");
        user.setPassword("123456");
        user.setBirth("20200101");
        user.setIndate(LocalDateTime.now());
        user.setLdate(LocalDateTime.now());
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("reolino2@gmail.com");
        user2.setName("reolino2");
        user2.setPassword("123456789");
        user2.setBirth("20201231");
        user2.setIndate(LocalDateTime.now());
        user2.setLdate(LocalDateTime.now());
        userRepository.save(user2);

        User user3 = new User("reolino3", "reolino3@gmail.com","123456789", "01011112222");
        User user4 = new User("reolino4", "reolino4@gmail.com","123456789", "01011113333");
        userRepository.saveAll(Lists.newArrayList(user3, user4));

        Page<User> users = userRepository.findAll(PageRequest.of(1,3));
        System.out.println("page :" + users);
        System.out.println("totalElements :" + users.getTotalElements());
        System.out.println("totalPages :" + users.getTotalPages());
        System.out.println("numberOfElement :" + users.getNumberOfElements());
        System.out.println("sort :" + users.getSort());
        System.out.println("size :" + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    @Test
    void crud3() {
        User user = new User();
        user.setEmail("reolino@gmail.com");
        user.setName("reolino");
        user.setPassword("123456");
        user.setBirth("20200101");
        user.setIndate(LocalDateTime.now());
        user.setLdate(LocalDateTime.now());
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("reolino2@gmail.com");
        user2.setName("reolino2");
        user2.setPassword("123456789");
        user2.setBirth("20201231");
        user2.setIndate(LocalDateTime.now());
        user2.setLdate(LocalDateTime.now());
        userRepository.save(user2);

        System.out.println("Like Case1 >>");
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnorePaths("name")
            .withIgnorePaths("password")
            .withIgnorePaths("phone")
            .withIgnorePaths("name")
            .withMatcher("email", endsWith());
        Example<User> example = Example.of(new User("reolino", "reolino2@gmail.com","123456789", "01011112222"), matcher);

        userRepository.findAll(example).forEach(System.out::println);

        System.out.println("Like Case2 >>");
        User user3 = new User();
        user3.setEmail("reolino2");

        ExampleMatcher matcher1 = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example1 = Example.of(user3, matcher1);
        userRepository.findAll(example1).forEach(System.out::println);
    }

    @Test
    void crud4() {
        userRepository.save(new User("reolino3", "reolino3@gmail.com","123456789", "01011112222"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("kychoi83@gmail.com");

        userRepository.save(user);
    }

    @Test
    void select() {
        userRepository.save(new User("reolino2", "reolino2@gmail.com","123456789", "01011112222"));
        userRepository.save(new User("reolino3", "reolino3@gmail.com","123456789", "01011112222"));

        System.out.println("findByName <<< "+userRepository.findByName("reolino2"));
//        System.out.println("findByEmail <<< "+userRepository.findByEmail("reolino2@gmail.com"));
//        System.out.println("getByEmail <<< "+userRepository.getByEmail("reolino2@gmail.com"));
//        System.out.println("readByEmail <<< "+userRepository.readByEmail("reolino2@gmail.com"));
//        System.out.println("queryByEmail <<< "+userRepository.queryByEmail("reolino2@gmail.com"));
//        System.out.println("searchByEmail <<< "+userRepository.searchByEmail("reolino2@gmail.com"));
//        System.out.println("streamByEmail <<< "+userRepository.streamByEmail("reolino2@gmail.com"));
//        System.out.println("findUserByEmail <<< "+userRepository.findUserByEmail("reolino2@gmail.com"));
        System.out.println("findTop1ByEmail <<< "+userRepository.findTop1ByEmail("reolino2@gmail.com"));
//        System.out.println("findFirst1ByEmail <<< "+userRepository.findFirst1ByEmail("reolino2@gmail.com"));
        System.out.println("findLast1ByEmail <<< "+userRepository.findLast1ByEmail("reolino2@gmail.com"));
        System.out.println("findByEmailAndName <<< "+userRepository.findByEmailAndName("reolino2@gmail.com","reolino2"));
        System.out.println("findByEmailOrName <<< "+userRepository.findByEmailOrName("reolino2@gmail.com","reolino2"));
        System.out.println("findByIndateAfter <<< "+userRepository.findByIndateAfter(LocalDateTime.now().minusDays(1L)));


    }
}
package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new EmailNotExistedException(email));
        //람다를 사용하여 아무때나 만들지 않도록 함
        //User가 있을경우 올바르게 돌려주고 없을경우 없다는것을 알려줌
        //맞는 email이 없는경우

        //비밀번호가 일치하지 않으면
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }

}

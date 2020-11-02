package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String name, String password) {
        Optional<User> existed = userRepository.findByEmail(email);
        if(existed.isPresent()){
            //existed.isPresent()(존재한다면 null이 아니라면) 예외를 발생
            //이미 같은 이메일이 등록되어있기때문에
            throw new EmailExistedException(email);
        }

        //PasswordEncoder는 인터페이스
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //PasswordEncoder를 상속받은 BCryptPasswordEncoder의 encode를 사용하여 password를 인코딩
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        //TODO
        return null;
    }
}

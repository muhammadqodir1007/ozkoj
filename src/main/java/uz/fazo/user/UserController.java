package uz.fazo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService service;
    private final UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto userDto1 = service.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
    }


}

package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.MemberDto;
import uz.fazo.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> members = memberService.getAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable long id) {
        MemberDto member = memberService.getById(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.create(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable int id, @RequestBody MemberDto memberDto) {
        MemberDto update = memberService.update(id, memberDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

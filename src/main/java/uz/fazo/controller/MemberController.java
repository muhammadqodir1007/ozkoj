package uz.fazo.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.MemberDto;
import uz.fazo.service.MemberService;

import java.io.IOException;
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

    @GetMapping("/export")
    public ResponseEntity<Resource> exportMembersToExcel() {
        try {
            byte[] excelBytes = memberService.exportMembersToExcel();
            ByteArrayResource resource = new ByteArrayResource(excelBytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=members.xlsx")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<List<MemberDto>> uploadClientsFromFile(@RequestParam("file") MultipartFile file) {
        try {
            List<MemberDto> clients = memberService.createFromFile(file);
            return ResponseEntity.ok(clients);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

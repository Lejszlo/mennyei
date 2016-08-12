package publicweb.controller.club;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@GetMapping("/{clubId}")
	public String getClubInfos() {
		return "Hello world";
	}
	
}

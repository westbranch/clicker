package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ClickerController {

	@Autowired
	private ClickerService clickerService;

	@MessageMapping("/count")
	@SendTo("/topic/click")
	public int incrementCounter() throws Exception {
		return clickerService.incrementCounterAndGet();
	}

	@MessageMapping("/current")
	@SendTo("/topic/click")
	public int getCurrentClickCount() {
		return clickerService.getCurrentClickCount();
	}

}

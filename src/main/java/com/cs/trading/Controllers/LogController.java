package com.cs.trading.Controllers;

import com.cs.trading.Models.Log;
import com.cs.trading.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {

	@Autowired
	LogService logService;

	@RequestMapping(value="/logs", method=RequestMethod.GET)
	public List<Log> returnAllLogs() {
		return logService.listAllLogs();
	}

	/*@RequestMapping(value="/logs/symbol/{symbol}", method=RequestMethod.GET)
	public List<Log> returnLogsBySymbol(@PathVariable(value="symbol") String symbol) {
		return ts.listAllLogsBySymbol(symbol);
	}


	@RequestMapping(value="/logs/traderid/{id}", method=RequestMethod.GET)
	public List<Log> returnAllLogsByTraderId(@PathVariable(value="id") int id) {
		return ts.listAllLogsByTraderId(id);
	}

	@RequestMapping(value="/logs/symbol", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public List<Log> findLogBySymbolWithFilters(String symbol, @RequestParam(value = "filter", required = false) String filter) {
		if(filter == null)
			return ts.listAllLogsBySymbol(symbol);
		else
			return ts.listAllLogsBySymbolWithFilters(symbol, filter);
	}*/
}


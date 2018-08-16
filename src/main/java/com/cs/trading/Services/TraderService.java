package com.cs.trading.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Trader;
import com.cs.trading.Models.Transaction;
import com.cs.trading.Repositories.TraderRepository;


@Component
public class TraderService {
	@Autowired
	TransactionService ts; 
	
	@Autowired
	TraderRepository tr; 

	public List<String> findTopNTradersByVolume(int limit){
		List<Trader> traderList = tr.findAll();
		//key: trader id; value: volume 
		HashMap<String, Integer> map = new HashMap<>();
		for(Trader t : traderList) {
			List<Transaction> tList = ts.listAllTransactionsByTraderId(t.getId());
			map.put(t.getId() + "", tList.size());
		}
		
		LinkedHashMap<String, Integer> result = 
				map.entrySet() 
				.stream() 
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		List<String> top5Traders = new ArrayList<>();
		int i = 0; 
		for(String keys: result.keySet()) {
//			System.out.println("----\n" + keys + "\n------");
			top5Traders.add(keys);
			i++;
			if(i == limit) {
				return top5Traders;
			}
		}
		return top5Traders;
	}
}

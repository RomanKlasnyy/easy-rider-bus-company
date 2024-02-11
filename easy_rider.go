package main

import (
	"encoding/json"
	"fmt"
	"os"
)

func main() {
	var data []map[string]string
	err := json.NewDecoder(os.Stdin).Decode(&data)
	if err != nil {
		fmt.Println("Error decoding JSON:", err)
		return
	}

	stopsByType := make(map[string][]string)
	for _, d := range data {
		stopType := d["stop_type"]
		stopName := d["stop_name"]
		stopsByType[stopType] = append(stopsByType[stopType], stopName)
	}

	var transferStops []string
	stopCounts := make(map[string]int)
	for _, stopList := range stopsByType {
		for _, name := range stopList {
			stopCounts[name]++
			if stopCounts[name] > 1 {
				transferStops = append(transferStops, name)
			}
		}
	}

	fmt.Println("On demand stops test:")
	var results []string
	for _, stop := range stopsByType["O"] {
		for _, tStop := range []string{"S", "F", ""} {
			for _, transfer := range transferStops {
				if contains(stopsByType[tStop], transfer) || contains(stopsByType[""], transfer) {
					results = append(results, stop)
					break
				}
			}
		}
	}

	if len(results) > 0 {
		fmt.Println("Wrong stop type:", results)
	} else {
		fmt.Println("OK")
	}
}

func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

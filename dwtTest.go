package main

import (
	"log"
	"time"
)


func main() {
	sim := NewTestDWTSimulation()
	var width int64 = 1024
	max := width * width
	sim.SetDisableEdge(true)
	sim.SetDisableRegOutputLogic(true)
	sim.InitConstants()
	sim.de_henningholm_da_DWTSimulation_width_tile = width
	sim.de_henningholm_da_DWTSimulation_height_tile = width
	sim.de_henningholm_da_DWTSimulation_count_resolutions = 1
	sim.de_henningholm_da_DWTSimulation_address_destination = max * 4
	count := 0
	log.Println("Started")
	for {
		if count > 5 {
			return
		}
		count += 1
		start := time.Now()
		sim.de_henningholm_da_DWTSimulation_clk = 1
		sim.de_henningholm_da_DWTSimulation_rst = 1
		sim.Run()
		log.Println("clk0 rst1")
		sim.de_henningholm_da_DWTSimulation_clk = 1
		sim.de_henningholm_da_DWTSimulation_rst = 0
		sim.de_henningholm_da_DWTSimulation_dwt_start = 1
		sim.Run()
		for {
			sim.Run()

			if sim.de_henningholm_da_DWTSimulation_dwt_done != 0 {
				break
			}
		}
		elapsed := time.Since(start)
		log.Printf("took %s\n", elapsed)
	}
}

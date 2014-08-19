//
//  main.c
//  DWTTest
//
//  Created by Karsten Becker on 05.08.14.
//  Copyright (c) 2014 PSHDL. All rights reserved.
//

#include <stdio.h>
#include <stdbool.h>
#include <stdint.h>
#include <time.h>
#include "pshdl_de_henningholm_da_DWTSimulation_sim.h"


int main(int argc, const char * argv[])
{
    int width=1024;
    int max=width*width;
    // insert code here...
    printf("Hello, World!\n");
    pshdl_sim_setDisableEdges(true);
    pshdl_sim_setDisableRegOutputlogic(true);

    pshdl_sim_initConstants();
    printf("Index of clk: %d\n", pshdl_sim_getIndex("de.henningholm.da.DWTSimulation.clk"));
    de_henningholm_da_DWTSimulation_width_tile = width;
    de_henningholm_da_DWTSimulation_height_tile = width;
    de_henningholm_da_DWTSimulation_count_resolutions = 1;
    de_henningholm_da_DWTSimulation_address_destination = max * 4;
    int count=0;
    while(true){
        if (count++>5)
            return 0;
        clock_t start = clock() / (CLOCKS_PER_SEC / 1000);
        de_henningholm_da_DWTSimulation_clk = 1;
        de_henningholm_da_DWTSimulation_rst = 1;
        pshdl_sim_run();
        de_henningholm_da_DWTSimulation_rst = 0;
        de_henningholm_da_DWTSimulation_dwt_start = 1;
        pshdl_sim_run();
        do {
            pshdl_sim_run();
        } while (de_henningholm_da_DWTSimulation_dwt_done == 0);
        clock_t end = clock() / (CLOCKS_PER_SEC / 1000);
        printf("Took %lu\n", end-start);
    }
    return 0;
}


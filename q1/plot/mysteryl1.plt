#please plot your results
set logscale xy
set terminal png truecolor
set output "output/mysteryL1.png"
set autoscale
set timefmt "%H:%M:%S"
plot "output/mystery1_l1.result" with linespoints
set output

quit

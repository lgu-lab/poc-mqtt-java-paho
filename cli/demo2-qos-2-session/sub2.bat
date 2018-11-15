REM  mosquitto_sub -v -h localhost -t a/b/c  
REM  mosquitto_sub -v -h localhost -t a/b/c     -q 2  -c  -i MYID

mosquitto_sub -v  -h localhost  -t a/b/c  -q 2  -c  -i MYID

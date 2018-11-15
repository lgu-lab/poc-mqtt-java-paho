
for /l %%x in (1, 1, 100) do (
   echo %%x
   mosquitto_pub -h test.mosquitto.org -t jug/nantes/txt -m "HELLO CLI %%x" -q 2 
   sleep 2
)

mosquitto_pub -h localhost -t a/b/c -m "HELLO WORLD"

for /l %%x in (1, 1, 100) do (
   echo %%x
   mosquitto_pub -h localhost -t a/b/c -m "HELLO WORLD %%x"
   sleep 2
)
data = ''
with open('ELECTRICSENSOR.csv', 'r') as f :
    data += '['
    a = 0
    for line in f.readlines():
        if a == 1:
            data += ","
        data += '{"mac" : "'+line.strip()+'",'
        data += '"latency" : "'+str(15)+'"'
        data += "}"
        a = 1
    data += ']'

with open('sensor.json', 'w') as f:
    f.write(data)

print(data)
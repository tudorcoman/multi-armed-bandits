
import requests
import numpy as np
import matplotlib.pyplot as plt 

link = "http://localhost:3000"
reporting_link = "http://localhost:8080"
number_of_tests = 4000
step = 100

def wipe_events():
    requests.delete(reporting_link + "/event")

wipe_events()

arm_conversion_rates = {
    1: 0.095,
    2: 0.02,
    3: 0.045,
}

mapping={4: 1, 5: 2, 6: 3}

arm_freq = [0, 0, 0, 0]
previous_conversion = None 

batch_arm_freq = []

for i in range(number_of_tests):
    if i % step == 0 and i > 0:
        print(f"Test {i}", arm_freq)
        batch_arm_freq.append(arm_freq[:])
        arm_freq = [0, 0, 0, 0]

    if previous_conversion is None:
        r = requests.get(link).json()
    else:
        r = requests.get(previous_conversion).json() 

    #print(r)
    arm = mapping[r["treatmentId"]]
    arm_freq[arm] += 1

    rnd = np.random.uniform()
    if rnd < arm_conversion_rates[arm]:
        previous_conversion = r["convert_url"] 
    else:
        previous_conversion = None 


batch_arm_freq.append(arm_freq)
percentage_of_allocation_to_winner = [armf[1] / float(step) * 100.0 for armf in batch_arm_freq]

print(percentage_of_allocation_to_winner)
plt.plot(range(0, number_of_tests, step), percentage_of_allocation_to_winner)
plt.xlabel("Test number")
plt.ylabel("Percentage of allocation to winner")
plt.show()

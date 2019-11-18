import sys
import csv
import numpy as np

preprocess_csv_file = sys.argv[1]
test_data_file = sys.argv[2]

data_array = []

with open(preprocess_csv_file, 'r') as csvfile:
    csvreader = csv.reader(csvfile)
    next(csvfile, None)
    for row in csvreader:
        data_array.append([float(x) for x in row[:]])

csvfile.close()

data_array = np.array(data_array)

features = []

for ch in [14]:
    features.append(round(np.mean(data_array[:, ch]), 9))
# for ch in range(32):
#     features.append(round(np.std(data_array[:, ch]), 9))
for ch in [5, 16]:
    pArr = data_array[:, ch] / sum(data_array[:, ch])
    features.append(round(-np.nansum(pArr * np.log2(pArr)), 9))
features.append("?")

print(features)

with open(test_data_file, 'a') as writeFile:
    # with open(test_data_file, 'a', newline="") as writeFile:
    #     writer = csv.writer(writeFile)
    writer = csv.writer(writeFile, lineterminator="\n")
    writer.writerow(features)
writeFile.close()

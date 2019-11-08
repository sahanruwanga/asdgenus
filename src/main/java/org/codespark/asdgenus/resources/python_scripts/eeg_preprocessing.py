import sys
import mne
import csv

# To turn information status messages off. ('info' to on)
mne.set_log_level('WARNING')


def eeg_filter(file_path):
    # Read given eeg file from file path and pick channels
    raw_data = mne.io.read_raw_brainvision(file_path, preload=True)
    raw_data.pick_types(meg=False, eeg=True, eog=False)

    # Apply average referencing
    raw_avg_ref, _ = mne.set_eeg_reference(raw_data, ref_channels='average')

    # Apply 1Hz high-pass filter for removing the baseline drift
    raw_avg_flt = raw_avg_ref.filter(1.0, None)

    # Apply notch filter for removing the AC power line noise
    raw_avg_notch = mne.filter.notch_filter(raw_avg_flt.to_data_frame().to_numpy(), 250.0, 60.0)
    return raw_avg_notch


# Input parameter should be a ndarray
def remove_eye_blink(data_array):
    for j in range(data_array.shape[1]):
        for i in range(1, len(data_array[:, j])):
            if abs(data_array[:, j][i]) > 300:
                data_array[:, j][i] = data_array[:, j][i]
    return data_array


def get_channel_names(eeg_file):
    # Read given eeg file from file path and pick channels
    raw_data = mne.io.read_raw_brainvision(eeg_file, preload=True)
    raw_data.pick_types(meg=False, eeg=True, eog=False)
    return raw_data.ch_names


eeg_file = sys.argv[1]
preprocess_data_file = sys.argv[2]
data = eeg_filter(eeg_file)
data_array = remove_eye_blink(data)

with open(preprocess_data_file, 'w', newline="") as writeFile:
    writer = csv.writer(writeFile)
    writer.writerow(get_channel_names(eeg_file))
    for i in range(data_array.shape[0]):
        writer.writerow(data_array[i])
writeFile.close()

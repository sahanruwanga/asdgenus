import sys
import mne
from datetime import datetime, timedelta

# To turn information status messages off. ('info' to on)
mne.set_log_level('WARNING')

eeg_path = sys.argv[1]
img_path = sys.argv[2]

raw_data = mne.io.read_raw_brainvision(eeg_path, preload=True)
duration = str(int(raw_data.n_times) / int(raw_data.info['sfreq']))
record_date = str(
    (datetime.fromtimestamp(raw_data.info['meas_date'][0]) - timedelta(hours=2)).strftime('%Y-%m-%d %H:%M:%S'))

mne.viz.plot_raw(raw_data, n_channels=32, show=False).savefig(img_path)
# image.savefig(sys.argv[2])
#image.savefig('/home/sahan/Desktop/sa.png')
print("ok")
print(duration)
print(record_date)

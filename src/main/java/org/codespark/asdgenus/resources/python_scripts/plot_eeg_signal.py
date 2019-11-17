import sys
import mne

# To turn information status messages off. ('info' to on)
mne.set_log_level('WARNING')

eeg_path = sys.argv[1]

raw_data = mne.io.read_raw_brainvision(eeg_path, preload=True)

# raw_data.plot(duration=5, n_channels=32, title="Original Signal")
image = mne.viz.plot_sensors(raw_data.info, show_names=True, show=False)
image.savefig(sys.argv[2])
print("ok")

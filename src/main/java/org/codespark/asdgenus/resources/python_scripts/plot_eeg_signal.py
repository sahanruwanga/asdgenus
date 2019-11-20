import sys
import mne

# To turn information status messages off. ('info' to on)
mne.set_log_level('WARNING')

eeg_path = sys.argv[1]

raw_data = mne.io.read_raw_brainvision(eeg_path, preload=True)

mne.viz.plot_raw(raw_data, n_channels=32, show=False).savefig(sys.argv[2])
# image.savefig(sys.argv[2])
#image.savefig('/home/sahan/Desktop/sa.png')
print("ok")

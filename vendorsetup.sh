#! /bin/bash

# Display hal
if ! [ -e "hardware/qcom-caf/sm8150/display/.git/refs/heads/thirteen" ]; then
echo "Cloning device suited clo display hal..."
rm -rf hardware/qcom-caf/sm8150/display && git clone --depth 1 https://github.com/tejas101k/hardware_qcom-caf_sm8150_display -b thirteen hardware/qcom-caf/sm8150/display
fi

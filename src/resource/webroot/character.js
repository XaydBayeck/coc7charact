var steps = document.querySelectorAll(".step")

for (let index = 0; index < steps.length; index++) {
    const step = steps[index];
    step.onclick = () => {
        if (step.className == "step") {
            step.className = "done"
        } else if (step.className == "done") {
            step.className = "step"
        }
    }
}

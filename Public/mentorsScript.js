const template = document.querySelector("#mentor-template");
const container = document.querySelector(".mentors-container");
const apiURL = "http://localhost:8000"

function getMentors() {
    fetch(`${apiURL}/mentor`)

        .then(function (response) {
        return response.json();
        })
        .then(function (mentors) {
            innerMentors(mentors);
        })
}


function innerMentors(mentors){
        mentors.forEach(mentor => {
            const clone = document.importNode(template.content, true);
            clone.querySelector('.name').textContent = mentor.name;
            clone.querySelector('.surname').textContent = mentor.surname;
            clone.querySelector('.email').textContent = mentor.email;
            clone.querySelector('.phone').textContent = mentor.phoneNumber;
            const edit = clone.querySelector('.popup');

            edit.addEventListener("click", popUp);

            const editName = clone.querySelector('#edit-name');
            editName.setAttribute("data-id", mentor.userDetailsID);
            editName.addEventListener("click", editNameFunction);

            const remButt = clone.querySelector('.removeButton');
            remButt.setAttribute("data-id", mentor.userDetailsID);
            remButt.addEventListener("click", removeMentor);
            container.appendChild(clone);
        })

}

function removeMentor(){
    const id = this.getAttribute("data-id");
    fetch(`${apiURL}/mentor/${id}/remove`, {
        // mode: 'cors',
        method: "POST" }
        )
        .then(function (response) {
            return response.json();

        })
        .then(function (mentors) {
            innerMentors(mentors);
            location.reload();
        });
}

function popUp() {
    // const id = this.getAttribute("data-id");
    var popup = this.lastElementChild;
    popup.classList.toggle("show");
}


function editNameFunction() {
    const id = this.getAttribute("data-id");
    console.log(id);
    const newName = prompt("Enter new name", 'name');
    console.log(newName);
    fetch(`${apiURL}/mentor/${id}/edit/${newName}`, {
        method: "POST" }
    )
        .then(function (response) {
            return response.json();

        })
        .then(function (mentors) {
            innerMentors(mentors)
            location.reload();
        });



}





getMentors();


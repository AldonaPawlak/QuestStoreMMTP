const template = document.querySelector("#mentor-template");
const container = document.querySelector(".mentors-container");


function getMentors() {
    fetch(`http://localhost:8050/mentor`)

        .then(function (response) {
        return response.json();
        })
        .then(function (mentors) {
            innerMentors(mentors);
        })
}


function innerMentors(mentors){
        mentors.forEach(mentor => {
            let clone = document.importNode(template.content, true);
            clone.querySelector('.name').textContent = mentor.name;
            clone.querySelector('.surname').textContent = mentor.surname;
            clone.querySelector('.email').textContent = mentor.email;
            clone.querySelector('.phone').textContent = "123123123";
            container.appendChild(clone);
        })

}



getMentors();


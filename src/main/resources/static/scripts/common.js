/* ======================================================
   TEAM PICKER – REUSABLE COMPONENT
====================================================== */
function initTeamPicker(config) {


    let selectedTeams = [];
    let highlightedIndex = -1;
    let suggestionsData = [];
    let debounceTimer = null;

    const input = document.getElementById(config.inputId);
    const suggestions = document.getElementById(config.suggestionsId);
    const tableBody = document.querySelector(`#${config.tableId} tbody`);
    const hiddenInput = config.hiddenInputId
        ? document.getElementById(config.hiddenInputId)
        : null;


    if (!input || !suggestions || !tableBody) {
        console.warn("TeamPicker: brak wymaganych elementów DOM", config);
        return;
    }

    function addTeam(team) {

        if (selectedTeams.length >= config.maxTeams) {
            alert(`Można wybrać maksymalnie ${config.maxTeams} drużyn`);
            return;
        }

        if (selectedTeams.some(t => t.id === team.id)) {
            alert("Ta drużyna jest już dodana");
            return;
        }

        selectedTeams.push(team);

        const row = document.createElement("tr");
        row.innerHTML = `
        <td>${team.name}</td>
        <td>${team.shortName ?? ""}</td>

        <td><img src="${team.crest}" width="30"></td>
        <td>
            <button type="button"
                    class="remove-team-btn"
                    onclick="${config.removeButtonFn}(this, ${team.id})">
                ❌
            </button>
        </td>
    `;

        tableBody.appendChild(row);

        // reset inputa i sugestii
        input.value = "";
        suggestions.innerHTML = "";
        highlightedIndex = -1;
    }
    /* =========================
       AUTOCOMPLETE INPUT
    ========================= */
    input.addEventListener("input", () => {

        clearTimeout(debounceTimer);

        debounceTimer = setTimeout(() => {

            const text = input.value.trim();

            if (text.length < 2) {
                suggestions.innerHTML = "";
                suggestionsData = [];
                highlightedIndex = -1;
                return;
            }

            fetch(config.searchUrl + encodeURIComponent(text))
                .then(res => res.json())
                .then(data => {

                    suggestions.innerHTML = "";
                    suggestionsData = data;
                    highlightedIndex = -1;

                    data.forEach((team, index) => {

                        const div = document.createElement("div");
                        div.classList.add("team-suggestion");

                        div.innerHTML = `
                            <img src="${team.crest}" width="20">
                            <span>${team.name}</span>
                        `;

                        div.onclick = () => addTeam(team);
                        suggestions.appendChild(div);
                    });
                });

        }, 300);
    });

    /* =========================
       KEYBOARD NAVIGATION
    ========================= */
    input.addEventListener("keydown", (e) => {

        const items = suggestions.querySelectorAll(".team-suggestion");
        if (!items.length) return;

        if (e.key === "ArrowDown") {
            e.preventDefault();
            highlightedIndex = (highlightedIndex + 1) % items.length;
            updateHighlight(items);
        }

        if (e.key === "ArrowUp") {
            e.preventDefault();
            highlightedIndex =
                (highlightedIndex - 1 + items.length) % items.length;
            updateHighlight(items);
        }

        if (e.key === "Enter" && highlightedIndex >= 0) {
            e.preventDefault();
            addTeam(suggestionsData[highlightedIndex]);
        }
    });

    function updateHighlight(items) {
        items.forEach(i => i.classList.remove("is-active"));
        if (items[highlightedIndex]) {
            items[highlightedIndex].classList.add("is-active");
        }
    }



    /* =========================
       REMOVE TEAM (GLOBAL FN)
    ========================= */
    window[config.removeButtonFn] = function (btn, teamId) {
        selectedTeams = selectedTeams.filter(t => t.id !== teamId);
        btn.closest("tr").remove();
    };

    /* =========================
       FORM SUBMIT (GLOBAL FN)
    ========================= */
    window[config.submitFn] = function () {

        if (selectedTeams.length !== config.maxTeams) {
            alert(`Musisz wybrać ${config.maxTeams} drużyn`);
            return false;
        }

        if (hiddenInput) {
            const ids = selectedTeams.map(t => t.id);

            hiddenInput.value =
                config.maxTeams === 1
                    ? ids[0]
                    : ids.join(",");
        }

        return true;
    };


}


/* ======================================================
   INIT – PORÓWNANIE (2 DRUŻYNY)
====================================================== */
initTeamPicker({
    inputId: "teamSearch",
    suggestionsId: "suggestions",
    tableId: "teamTable",
    hiddenInputId: "teamsIds",
    maxTeams: 2,
    removeButtonFn: "removeTeam",
    submitFn: "submitCompare",
    searchUrl: "/druzyny/szukaj?text="
});

/* ======================================================
   INIT – STATYSTYKI (1 DRUŻYNA)
====================================================== */
initTeamPicker({
    inputId: "teamStatsSearch",
    suggestionsId: "suggestion",
    tableId: "teamStatsTable",
    hiddenInputId: "teamStatsId",
    maxTeams: 1,
    removeButtonFn: "removeStatsTeam",
    submitFn: "submitStats",
    searchUrl: "/druzyny/szukaj?text="
});

function toggleMatches(button) {
    const wrapper = button.closest('.last-matches-block');
    const table = wrapper.querySelector('.last-matches-table');

    table.classList.toggle('expanded');

    button.textContent = table.classList.contains('expanded')
        ? 'Pokaż mniej'
        : 'Pokaż więcej';
}

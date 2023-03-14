
function openNav() {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById("openbtn").style.opacity ="0";
}

function closeNav() {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.getElementById("openbtn").style.opacity ="0";
}

function closeSidebar() {
    document.getElementById("openbtn").style.width = "0";
    document.getElementById("openbtn").style.marginLeft = "0";
}
	
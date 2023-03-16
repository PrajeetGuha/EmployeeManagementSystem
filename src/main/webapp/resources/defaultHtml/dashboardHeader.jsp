<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
        <title>ADMIN DASHBOARD</title>
        <link rel="stylesheet" href="../resources/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../resources/custom/css/admin-dashboard/custom.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
          rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

        <c:set var="pageNo" value="${pageNo}" />
        <c:set var="pageCount" value="${pageCount}" />
        <script>
          $(document)
            .ready(
              function () {
                // Loop through each cell in the highlight-column class
                $('.highlight-column')
                  .each(
                    function () {
                      var status = $(this).text(); // Get the cell's text value

                      // Set a different background color based on the status value
                      if (status === 'active') {


                        $(this).css('color', 'limegreen');

                      } else if (status === 'inactive') {


                        $(this).css('color', 'red');
                      }
                    });
              });
        </script>
        <script>
          $(document).ready(function () {
            // Get the list of items from the model attribute
            var items = [
              <c:forEach var="item" items="${allemployeenames}">
                "${item}",
              </c:forEach>
            ];

            // Set the initial form action
            /*  var initialUrl = window.location.href.split('?')[0];
           $('#search-form').attr('action', initialUrl + '?search=null&pg=1');
            */

            // Listen for the keyup event on the search input field
            $('#search-input').on('keyup', function () {
              // Get the value of the search input field
              var searchTerm = $(this).val().toLowerCase();

              // Filter the list based on the search term
              var filteredItems = items.filter(function (item) {
                return item.toLowerCase().indexOf(searchTerm) > -1;
              });

              // Update the search results list
              var $searchResults = $('#search-results');
              $searchResults.empty();
              filteredItems.forEach(function (item) {
                var $li = $('<li>').text(item);
                $li.on('click', function () {
                  $('#search-input').val($(this).text());
                  $searchResults.hide();


                });
                $searchResults.append($li);
              });

              // Show/hide the search results list
              if (searchTerm.length > 0) {
                $searchResults.show();
              } else {
                $searchResults.hide();
              }
            });

            // Hide the search results list on document click
            $(document).on('click', function (event) {
              if (!$(event.target).closest('.xp-searchbar').length) {
                $searchResults.hide();
              }
            });

            // Update form action when GO button is clicked
            $('#button-addon2').on('click', function (event) {
              event.preventDefault();
              updateFormAction();
            });

            function updateFormAction() {
              var searchTerm = $('#search-input').val();
              if (searchTerm.trim() === '') {
                searchTerm = 'null';
              }
              var url = 'http://localhost:8081/admin/dashboard?search=null'; // replace with your URL
              var newUrl = url.replace('search=null', 'search=' + encodeURIComponent(searchTerm) + '&pg=1');
              window.location.href = newUrl;
            }



          });
        </script>

      </head>

      <c:set var="pageNo" value="${pageNo}" />
      <c:set var="pageCount" value="${pageCount}" />
      </head>
      <script>
        function selectedEmpstatus(id, name, status) {
          document.getElementById("empstatusname").innerHTML = name;
          $("#empIdStatus").attr("value", id);
          if (status == "inactive") {
            $("#status-modal-form").attr("action", "activateUser?search=${search}&pgNo=${pageNo}");
            $("#changestatusbtn").attr("class", "btn btn-primary");
            $("#changestatusbtn").attr("value", "Activate");
          }
          else {
            $("#status-modal-form").attr("action", "deactivateUser?search=${search}&pgNo=${pageNo}");
            $("#changestatusbtn").attr("class", "btn btn-danger");
            $("#changestatusbtn").attr("value", "Deactivate");
          }
        }
      </script>


      <body>
        <!-- <div>${result.getBody().getStatus()}</div> -->

        <div class="wrapper">
          <div class="body-overlay" />
          <nav id="sidebar">
            <div class="sidebar-header">
              <h3>
                <img src="../resources/assets/logo.png" class="img-fluid" /><span>NRI
                  Fintech</span>
              </h3>
            </div>
            <ul class="list-unstyled components">
              <li class="active"><a href="#" class="dashboard"><i class="material-icons">dashboard</i>
                  <span>Dashboard</span></a></li>
              <li><a href="#homeSubmenu1" data-toggle="collapse" aria-expanded="false"> <i
                    class="material-icons">playlist_add_check</i>Apply Leave
                  
                </a></li>

                
                <li><a href="#empresignation" data-toggle="modal" aria-expanded="false"> <i
                  class="material-icons">directions_walk</i>Resignation
              approval


          </a></li>
                
              <li ><a href="employeepersonaldetails" >
                <!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
                <span>Personal Details</span></a></li>
                
                
              <li ><a href="professionaldetails" >
                <!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
                <span>Professional Details</span></a></li>
                
              <li ><a href="qualificationdetails" >
                <!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
                <span>Qualification Details</span></a></li>
              <li ><a href="familyDetails" >
                <!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
                <span>Family Details</span></a></li>

             


            </ul>


          </nav>











          <!--------page-content---------------->

          <div id="content">

            <!--top--navbar----design--------->
            <div class="top-navbar">
              <div class="xp-topbar">

                  <!-- Start XP Row -->
                  <div class="row justify-content-end">
                      <!-- Start XP Col -->
                      <!-- <div
    class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
    <div class="xp-menubar">
      <span class="material-icons text-white">signal_cellular_alt
      </span>
    </div>
  </div> -->
                      <!-- End XP Col -->

                      <!-- Start XP Col -->



                      <!-- End XP Col -->

                      <!-- Start XP Col -->
                      <div class="col-md-3 col-lg-2 order-1 order-md-3 ">
                          <div class="xp-profilebar text-right" align="right">
                              <nav class="navbar p-0">
                                  <ul class="nav navbar-nav flex-row ml-auto">
                                      <li class="align-right"><a href="../logout" class="nav-link"><span
                                                  class="material-icons">logout</span>
                                              Logout</a></li>

                                  </ul>


                              </nav>

                          </div>
                      </div>
                      <!-- End XP Col -->

                  </div>
                <!-- End XP Row -->

              </div>
              <div class="xp-breadcrumbbar text-center">
                <h4 class="page-title">Dashboard</h4>
                <!--  <ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Booster</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol>-->
              </div>

            </div>



            <!--------main-content------------->






                <!---footer---->


              </div>

            </div>
          </div>


          <!----------html code complete----------->





        </div>



        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->

      </body>
      <script src="../resources/lib/jquery/jquery-3.3.1.min.js" type="text/javascript" />
      <script src="../resources/lib/jquery/jquery-3.3.1.slim.min.js" type="text/javascript" />
      <script src="../resources/custom/js/admin-dashboard/navtoggle.js" type="text/javascript" />
      <script src="../resources/lib/popper/popper.min.js" type="text/javascript" />
      <script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript" />


      <script type="text/javascript">

        $(document).ready(function () {
          $(".xp-menubar").on('click', function () {
            $('#sidebar').toggleClass('active');
            $('#content').toggleClass('active');
          });

          $(".xp-menubar,.body-overlay").on('click', function () {
            $('#sidebar,.body-overlay').toggleClass('show-nav');
          });

        });

      </script>

      </html>
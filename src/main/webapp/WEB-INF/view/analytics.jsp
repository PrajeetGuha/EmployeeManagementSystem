<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>ADMIN DASHBOARD</title>
<link rel="stylesheet"
	href="../resources/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../resources/custom/css/admin-dashboard/analytics.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Icons"
	rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.3/apexcharts.min.js"></script>

</head>


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

				<li ><a href="dashboard?search=null&pg=1" class="dashboard"><i
						class="material-icons">dashboard</i> <span>Dashboard</span></a></li>
				<li><a href="projectallocation?pg=1"> <i class="material-icons">laptop</i>Project
				</a></li>
				<li ><a href="teamallocation?pg=1"> <i class="material-icons">groups</i>Team
				</a></li>
				<!-- <li><a href="departmentallocation?pg=1"> <i
						class="material-icons">work</i>Department
				</a></li> -->

								<li><a href="leaveApproval?pg=1"> <i class="material-icons">playlist_add_check</i>Leave
										Approval
									</a></li>
								<li><a href="resignationApproval?pg=1" > <i
											class="material-icons">directions_walk</i>Resignation
										approval
									</a></li>
				<li class="active"><a href="analytics"  aria-expanded="false">
						<i class="material-icons">analytics</i>Analytics
				</a></li>
				<!-- <li><a href="#changePasswordModal" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">vpn_key</i>Change Password

				</a></li> -->
				<li><a href="#adminprofile" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">account_circle</i>Profile
				</a></li>


			</ul>


		</nav>
		

		<div id="adminprofile" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="hike" method="post">
						<div class="modal-header">
							<h4 class="modal-title">ADMIN PROFILE</h4>
							
						</div>
						<div class="modal-body">
							<ul>
								<li>
									<p>
										<b>Admin id:</b> ${admin.adminId}
									</p>
								</li>
								<li>
									<p>
										<b>Admin name:</b> ${admin.adminName}
									</p>
								</li>
								<li>
									<p>
										<b>Admin email:</b> ${admin.adminEmail}
									</p>
								</li>
							</ul>
						</div>

						<div class="modal-footer">
							<input type="button" class="btn btn-secondary" data-dismiss="modal"
								value="Close">

						</div>
					</form>
				</div>
			</div>
		</div>






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
						<%-- <div class="col-md-5 col-lg-2 order-3 order-md-2">
							<div class="xp-searchbar">
								<form id="search-form" action="" method="get">
									<input type="hidden" name="search" value="">
									<div class="input-group">
										<input type="search" class="form-control" placeholder="Search"
											id="search-input">
										<div class="input-group-append">
											<button class="btn" type="submit" id="button-addon2">GO</button>
										</div>
									</div>
								</form>
								<ul class="dropdown-menu" id="search-results"
									style="display: none;">
								</ul>
							</div>
						</div> --%>


						<!-- End XP Col -->

						<!-- Start XP Col -->
						<div class="col-md-3 col-lg-2 order-1 order-md-3 ">
							<div class="xp-profilebar text-right" align="right">
								<nav class="navbar p-0">
									<ul class="nav navbar-nav flex-row ml-auto">
										<li class="align-right"><a href="../logout"
											class="nav-link"><span class="material-icons">logout</span>
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
					<h4 class="page-title">Analytics</h4>
					
				</div>

			</div>



			<!--------main-content------------->

			
		<main class="main-container">
        

        <div class="main-cards">

          <div class="card">
            <div class="card-inner">
              <p class="text-primary">EMPLOYEES</p>
              <span class="material-icons">analytics</span>
            </div>
            <span class="text-primary font-weight-bold">${countOfEmployees}</span>
          </div>

          <div class="card">
            <div class="card-inner">
              <p class="text-primary">DEPARTMENTS</p>
              <span class="material-icons">devices</span>
            </div>
            <span class="text-primary font-weight-bold">${countOfDepartments}</span>
          </div>

          <div class="card">
            <div class="card-inner">
              <p class="text-primary">TEAMS</p>
              <span class="material-icons">groups</span>
            </div>
            <span class="text-primary font-weight-bold">${countOfTeams}</span>
          </div>
          
          <div class="card">
            <div class="card-inner">
              <p class="text-primary">PROJECTS</p>
              <span class="material-icons">laptop</span>
            </div>
            <span class="text-primary font-weight-bold">${countOfProjects}</span>
          </div>


        </div>
		<div class="charts">

          <div class="charts-card">
            <p class="chart-title">Employees in each Department</p>
            <div id="bar-chart"></div>
          </div>

           <!--  <div class="charts-card">
            <p class="chart-title">Sex Ratio</p>
            <div id="chart"></div>
          </div> -->
           <div class="charts-card">
            <p class="chart-title">Total Cost Distribution of Each Department</p>
            <div id="bar-chart2"></div>
          </div> 
           <div class="charts-card">
            <p class="chart-title">Employee Type</p>
            <div id="pie-chart"></div>
          </div> 
			   <div class="charts-card">
            <p class="chart-title">Recruitment Metrics</p>
            <div id="line-chart"></div>
          </div> 
        </div>
        

       
      </main>



		

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

</body>
<script src="../resources/lib/jquery/jquery-3.3.1.min.js"
	type="text/javascript" />
<script src="../resources/lib/jquery/jquery-3.3.1.slim.min.js"
	type="text/javascript" />
<script src="../resources/custom/js/admin-dashboard/navtoggle.js"
	type="text/javascript" />
<script src="../resources/lib/popper/popper.min.js"
	type="text/javascript" />
<script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js"
	type="text/javascript" />


<script type="text/javascript">
        
		$(document).ready(function(){
		  $(".xp-menubar").on('click',function(){
		    $('#sidebar').toggleClass('active');
			$('#content').toggleClass('active');
		  });
		  
		   $(".xp-menubar,.body-overlay").on('click',function(){
		     $('#sidebar,.body-overlay').toggleClass('show-nav');
		   });
		  
		});
		
</script>
<script type="text/javascript">
var barChartOptions = {
		  series: [{
		    data: [<c:forEach var="item" items="${countOfEmployeesInDepartment}">
		      "${item}",
		      </c:forEach>]
		  }],
		  chart: {
		    type: 'bar',
		    height: 350,
		    toolbar: {
		        show: true,
		        tools: {
		          download: true,
		          selection: true,
		          zoom: true,
		          zoomin: true,
		          zoomout: true,
		          pan: true,
		          reset: true
		        },
		        autoSelected: 'zoom'
		      },
		  },
		  colors: [
		    "#246dec",
		    "#cc3c43",
		    "#367952",
		    "#f5b74f",
		    "#4f35a1"
		  ],
		  plotOptions: {
		    bar: {
		      distributed: true,
		      borderRadius: 4,
		      horizontal: false,
		      columnWidth: '40%',
		    }
		  },
		  dataLabels: {
		    enabled: false
		  },
		  legend: {
		    show: false
		  },
		  xaxis: {
		    categories: [ <c:forEach var="item" items="${alldepartmentnames}">
		      "${item}",
		      </c:forEach>],
		      title: {
			      text: "Department"
			    }
		  },
		  yaxis: {
		    title: {
		      text: "Count"
		    }
		  }
		};

		var barChart = new ApexCharts(document.querySelector("#bar-chart"), barChartOptions);
		barChart.render();


		/* var chartOptions = {
			        chart: {
			            type: 'pie',
			            height: 350,
			            
			        },
			        series: [<c:forEach var="item" items="${sexratio}">
				      "${item}",
				      </c:forEach>],
			        labels: ['Male', 'Female','Other'],
			        colors: ['#008FFB', '#00E396', '#4f35a1'],
			        
			        
			        dataLabels: {
			            enabled: true
			          }
			    }
			    
			    var chart = new ApexCharts(document.querySelector("#chart"), chartOptions);
			    chart.render();*/
			    
			    /*var options = {
			            series: [${sexratio[0]}, ${sexratio[1]}, ${sexratio[2]}],
			            chart: {
			            width: 400,
			            type: 'donut',
			            dropShadow: {
			              enabled: true,
			              color: '#111',
			              top: -1,
			              left: 3,
			              blur: 3,
			              opacity: 0.2
			            }
			          },
			          stroke: {
			            width: 0,
			          },
			          plotOptions: {
			            pie: {
			              donut: {
			                labels: {
			                  show: true,
			                  total: {
			                    showAlways: true,
			                    show: true
			                  }
			                }
			              }
			            }
			          },
			          labels: ["Male", "Female", "Other"],
			          dataLabels: {
			            dropShadow: {
			              blur: 3,
			              opacity: 0.8
			            }
			          },
			          fill: {
			          type: 'pattern',
			            opacity: 1,
			            pattern: {
			              enabled: true,
			              style: ['squares', 'squares', 'squares'],
			            },
			          },
			          states: {
			            hover: {
			              filter: 'none'
			            }
			          },
			          theme: {
			            palette: 'palette2'
			          },
			        
			          responsive: [{
			            breakpoint: 480,
			            options: {
			              chart: {
			                width: 200
			              },
			              
			            }
			          }]
			          };

			          var chart = new ApexCharts(document.querySelector("#chart"), options);
			          chart.render();*/
			    
			    var barChart2Options = {
			  		  series: [{
			  		    data: [<c:forEach var="item" items="${totalcost}">
			  		      "${item}",
			  		      </c:forEach>]
			  		  }],
			  		  chart: {
			  		    type: 'bar',
			  		    height: 350,
			  		  toolbar: {
			  		    show: true,
			  		    tools: {
			  		      download: true,
			  		      selection: true,
			  		      zoom: true,
			  		      zoomin: true,
			  		      zoomout: true,
			  		      pan: true,
			  		      reset: true
			  		    },
			  		    autoSelected: 'zoom'
			  		  },
			  		  },
			  		  colors: [
			  		    "#246dec",
			  		    "#cc3c43",
			  		    "#367952",
			  		    "#f5b74f",
			  		    "#4f35a1"
			  		  ],
			  		  plotOptions: {
			  		    bar: {
			  		      distributed: true,
			  		      borderRadius: 4,
			  		      horizontal: false,
			  		      columnWidth: '40%',
			  		    }
			  		  },
			  		  dataLabels: {
			  		    enabled: false
			  		  },
			  		  legend: {
			  		    show: false
			  		  },
			  		  xaxis: {
			  		    categories: [ <c:forEach var="item" items="${alldepartmentnames}">
			  		      "${item}",
			  		      </c:forEach>],
			  		    title: {
				  		      text: "Total cost"
				  		    }
			  		  },
			  		  yaxis: {
			  		    title: {
			  		      text: "Department"
			  		    }
			  		  }
			  		};

			  		var barChart2 = new ApexCharts(document.querySelector("#bar-chart2"), barChart2Options);
			  		barChart2.render();
			  		
			  		var pieoptions = {
			  	          series: [${emptype[0]}, ${emptype[1]}, ${emptype[2]}],
			  	          chart: {
			  	          width: 380,
			  	          type: 'pie',
			  	        toolbar: {
			  	          show: true,
			  	          tools: {
			  	            download: true,
			  	          selection: true,
			  		      zoom: true,
			  		      zoomin: true,
			  		      zoomout: true,
			  		      pan: true,
			  		      reset: true,
			  	            // You can add more built-in toolbar options here
			  	          },
			  	        autoSelected: 'zoom',
			  	        },
			  	          
			  	        },
			  	        labels: ['Full-Time', 'Part-Time', 'Contract'],
			  	        responsive: [{
			  	          breakpoint: 480,
			  	          options: {
			  	            chart: {
			  	              width: 200
			  	              
			  	              
			  	            },
			  	            legend: {
			  	              position: 'bottom'
			  	            }
			  	          }
			  	        }]
			  	        };

			  	        var piechart = new ApexCharts(document.querySelector("#pie-chart"), pieoptions);
			  	        piechart.render();
			  	        
			  	      var options = {
			  	            series: [{
			  	              name: "Employees",
			  	              data: [<c:forEach var="item" items="${recruitment}">
				  		      "${item}",
				  		      </c:forEach>]
			  	          }],
			  	            chart: {
			  	            height: 350,
			  	            type: 'line',
			  	            zoom: {
			  	              enabled: false
			  	            }
			  	          },
			  	          dataLabels: {
			  	            enabled: false
			  	          },
			  	          stroke: {
			  	            curve: 'straight'
			  	          },
			  	          title: {
			  	            text: 'Recruitments per Month',
			  	            align: 'left'
			  	          },
			  	          grid: {
			  	            row: {
			  	              colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
			  	              opacity: 0.5
			  	            },
			  	          },
			  	          xaxis: {
			  	            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep','Oct','Nov','Dec'],
			  	          title: {
				  		      text: "Month"
				  		    }
			  	          }
			  	      
			  	          };

			  	          var linechart = new ApexCharts(document.querySelector("#line-chart"), options);
			  	          linechart.render();
			  	        
			  	  		/*var pieoptions2 = {
					  	          series: [ <c:forEach var="item" items="${findteamcount}">
					  		      "${item}",
					  		      </c:forEach>],
					  	          chart: {
					  	          width: 380,
					  	          type: 'pie',
					  	        },
					  	        labels: [ <c:forEach var="item" items="${teamsinprojects}">
					  		      "${item}",
					  		      </c:forEach>],
					  	        responsive: [{
					  	          breakpoint: 480,
					  	          options: {
					  	            chart: {
					  	              width: 200
					  	            },
					  	            legend: {
					  	              position: 'bottom'
					  	            }
					  	          }
					  	        }]
					  	        };

					  	        var piechart2 = new ApexCharts(document.querySelector("#pie-chart2"), pieoptions2);
					  	        piechart2.render();*/
					  	       
			  	        

			    
		
		// AREA CHART
		/*var areaChartOptions = {
		  series: [{
		    name: 'Purchase Orders',
		    data: [31, 40, 28, 51, 42, 109, 100]
		  }, {
		    name: 'Sales Orders',
		    data: [11, 32, 45, 32, 34, 52, 41]
		  }],
		  chart: {
		    height: 350,
		    type: 'area',
		    toolbar: {
		      show: false,
		    },
		  },
		  colors: ["#4f35a1", "#246dec"],
		  dataLabels: {
		    enabled: false,
		  },
		  stroke: {
		    curve: 'smooth'
		  },
		  labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
		  markers: {
		    size: 0
		  },
		  yaxis: [
		    {
		      title: {
		        text: 'Purchase Orders',
		      },
		    },
		    {
		      opposite: true,
		      title: {
		        text: 'Sales Orders',
		      },
		    },
		  ],
		  tooltip: {
		    shared: true,
		    intersect: false,
		  }
		};

		var areaChart = new ApexCharts(document.querySelector("#area-chart"), areaChartOptions);
		areaChart.render();*/
</script>


</html>
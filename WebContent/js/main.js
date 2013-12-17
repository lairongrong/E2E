// The root URL for the RESTful services
var rootURL = "http://localhost:8080/zuoyou/rest/users";

var currentUser;

// Retrieve user list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#userList a').live('click', function() {
	findById($(this).data('identity'));
});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}


function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentUser = data;
			renderDetails(currentUser);
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#userList li').remove();
	$.each(list, function(index, user) {
		$('#userList').append('<li><a href="#" data-identity="' + user.uid + '">'+user.name+'</a></li>');
	});
}

function renderDetails(user) {
	$('#userId').val(user.uid);
	$('#name').val(user.name);
	$('#contactid').val(user.contactInfoId);
	$('#activestatus').val(user.activeStatus);
	$('#roleid').val(user.roleId);
}

//// Helper function to serialize all the form fields into a JSON string
//function formToJSON() {
//	var userId = $('#usrId').val();
//	return JSON.stringify({
//		"id": userId == "" ? null : userId, 
//		"name": $('#name').val(), 
//		"contactid": $('#contactid').val(),
//		"activestatus": $('#activestatus').val(),
//		"roleid": $('#roleid').val()
//		});
//}

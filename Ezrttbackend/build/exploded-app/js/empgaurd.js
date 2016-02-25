function onSignIn(googleUser){
        var profile=googleUser.getBasicProfile();
        console.log('ID: '+profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: '+profile.getName());
        console.log('Image URL: '+profile.getImageUrl());
        console.log('Email: '+profile.getEmail());
        var but = document.getElementById('button');
        var signed = document.getElementById('sign-in');
        but.removeChild(signed);
        document.getElementById('loggeduser').innerHTML='Hello, '+profile.getName()+'!';
        document.getElementById('button').innerHTML='<a id="logout" href="#" onclick="signOut();" style="float: right;">Sign out</a>';
        }

function signOut(){
        var auth2=gapi.auth2.getAuthInstance();
        auth2.signOut().then(function(){
        console.log('User signed out.');
        });

        var but = document.getElementById('button');
        var logged = document.getElementById('logout');
        but.removeChild(logged);
        document.getElementById('button').innerHTML='<div id="sign-in" class="g-signin2" data-onsuccess="onSignIn"
                                                                       style="float: right;"></div>';
        }

function toggle_visibility(id){
        var e=document.getElementById(id);
        if(e.style.display=='visible')
        e.style.display='none';
        else
        e.style.display='visible';
        }

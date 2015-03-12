Name:           test2
Version:        %{?VERSION}
Release:        1
Summary:        The "Test" thing for gradle/rpm plugin
License:        GPL
Source0:        test.ear      
Group:          Test Group
BuildArch:      noarch
BuildRoot:      %{_tmppath}/%{name}-buildroot
AutoReqProv:    no


%description 
This is a dummy rpm used to test simplerpm plugin for gradle

#Disable jar repacking and debug_package creation
%define __os_install_post %{nil}
%define debug_package %{nil}

%prep
install -m 644 %SOURCE0 .


%build

%install
rm -rf %{buildroot}
mkdir -p %{buildroot}/tmp/test
cp -a test.ear %{buildroot}/tmp/test

%files
%defattr(-,root,root)
/tmp/test

%pre
%post
%preun
%postrun 


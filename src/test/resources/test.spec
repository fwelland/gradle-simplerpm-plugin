Name:           test
Version:        2.8
Release:        1%{?dist}
Summary:        The "Test" thing for gradle/rpm plugin
License:        GPL
Source0:        test.ear      
Requires(post): info
Requires(preun): info

%description 
The "Hello World" program, done with all bells and whistles of a proper FOSS 
project, including configuration, build, internationalization, help files, etc.

%prep
%autosetup

%build
%configure

%install
%make_install

%post


%preun
%files
%changelog
- Initial version of the package

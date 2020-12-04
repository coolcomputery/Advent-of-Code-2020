#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
using namespace std;
typedef long long ll;
typedef vector<ll> vll;
typedef vector<string> vs;
typedef vector<vll> vvll;
typedef pair<ll,ll> pll;
#define PB push_back
vs fields{"byr","iyr","eyr","hgt","hcl","ecl","pid"};//,"cid"}
int main() {
    vector<vector<string>> ports;
    for (vector<string> port{}; true; ) {
        string lin;
        getline(cin,lin);
        if (lin.size()<=2) {
            vector<string> tmp;
            for (string s:port)
                tmp.PB(s);
            ports.PB(tmp);
            port.clear();
            //break;
            if (lin.size()==0) break;
        }
        else
            port.PB(lin);
    }
    ll ans=0;
    for (vs port:ports) {
        /*cout<<"port\n";
        for (string s:port)
            cout<<s<<"\n";*/
        vs found;
        for (string s:port) {
            while (true) {
                found.PB(s.substr(0,s.find(':')));
                if (s.find(' ')==-1) break;
                s=s.substr(s.find(' ')+1);
            }
        }
        bool good=true;
        for (string s:fields) {
            bool there=false;
            for (string s1:found)
            if (s1==s) there=true;
            if (!there) good=false;
        }
        ans+=good;
    }
    cout<<ans;
    
}

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
using namespace std;
typedef long long ll;
typedef vector<ll> vll;
typedef vector<vll> vvll;
typedef pair<ll,ll> pll;
#define PB push_back
int main() {
    ll goods=0;
    for (ll t=0; t<1000; t++) {
        string s;
        getline(cin,s);
        ll si=s.find(':');
        string s0=s.substr(0,si);
        //cout<<s0<<"\n";
        char c=s0.at(s0.size()-1);
        ll dash=s0.find('-');
        ll lo=stoi(s0.substr(0,dash)), hi=stoi(s0.substr(dash+1,s0.size()-2-(dash+1)));
        //cout<<"lo="<<lo<<" hi="<<hi<<" c="<<c<<"\n";
        string s1=s.substr(si+2,s.size()-(si+2));
        if ((s1.at(lo-1)==c) ^ (s1.at(hi-1)==c)) goods++;
    }
    cout<<goods;
}

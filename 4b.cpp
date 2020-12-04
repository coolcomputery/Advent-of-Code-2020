#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <map>
#include <set>
using namespace std;
typedef long long ll;
typedef vector<ll> vll;
typedef vector<string> vs;
typedef vector<vll> vvll;
typedef pair<ll,ll> pll;
typedef pair<string,string> pss;
#define PB push_back
vs fields{"byr","iyr","eyr","hgt","hcl","ecl","pid"};//,"cid"}
set<string> ecls={"amb","blu","brn","gry","grn","hzl","oth"};
/*
byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
*/

bool btwn(ll x, ll a, ll b) {
    return a<=x && x<=b;
}
bool isnum(string s, int base) {
    //https://stackoverflow.com/questions/4654636/how-to-determine-if-a-string-is-a-number-with-c
    char *p;
    strtol(s.c_str(),&p,base);
    return *p==0;
}
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
        //cout<<"port\n";
        /*
        for (string s:port)
            cout<<s<<"\n";*/
        map<string,string> info;
        for (string s:port) {
            while (true) {
                ll c=s.find(':');
                ll sp=s.find(' ');
                string val="";
                for (ll i=c+1; i<(sp==-1?s.size():sp) && btwn(s.at(i),33,126); i++)
                    val+=s.at(i);
                //string val=s.substr(c+1,(sp==-1?s.size():sp)-(c+1));
                if (val.at(val.size()-1)=='\n')
                    val=val.substr(0,val.size()-2);
                info.insert(pair<string,string>(
                    s.substr(0,c),val
                ));
                if (sp==-1) break;
                s=s.substr(s.find(' ')+1);
            }
        }
        /*for (pss p:info)
            cout<<p.first<<":"<<p.second<<"--";
        cout<<"\n";*/
        bool good=true;
        for (string s:fields) {
            if (info.find(s)==info.end()) good=false;
        }
        if (good) {
            string h=info["hgt"];
            string e=h.substr(h.size()-2);
            if (e!="cm" && e!="in") good=false;
            for (ll i=0; i<h.size()-2; i++)
                if (!btwn(h.at(i),'0','9')) good=false;
            if (good) {
                ll hi=stoi(h.substr(0,h.size()-2));
                good=good&&(e=="cm"?btwn(hi,150,193):btwn(hi,59,76));
            }
        }
        good=good&&btwn(stoi(info["byr"]),1920,2002)
                &&btwn(stoi(info["iyr"]),2010,2020)
                &&btwn(stoi(info["eyr"]),2020,2030);
        if (good) {
            string c=info["hcl"];
            good=good&&c.at(0)=='#'&&c.size()==7&&isnum(c.substr(1),16);
        }
        good=good&&(ecls.find(info["ecl"])!=ecls.end());
        if (good) {
            string h=info["pid"];
            for (ll i=0; i<h.size(); i++)
                if (!btwn(h.at(i),'0','9')) good=false;
            good=good&&h.size()==9;
        }
        ans+=good;
    }
    cout<<ans;
    
}

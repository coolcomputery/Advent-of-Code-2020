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
ll board[1000][100];
ll cnt(ll r, ll d) {
    ll cnt=0;
    for (ll i=0, j=0; i<323; i+=d, j+=r)
        if (board[i][j%31]==1)
            cnt++;
            return cnt;
}
int main() {
    ll N=323;
    for (ll i=0; i<N; i++) {
        string s; cin>>s;
        for (ll j=0; j<s.size(); j++)
            board[i][j]=s.at(j)=='#'?1:0;
    }
    cout<<cnt(1,1)*cnt(3,1)*cnt(5,1)*cnt(7,1)*cnt(1,2);
}

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
int main() {
    ll N=323;
    for (ll i=0; i<N; i++) {
        string s; cin>>s;
        for (ll j=0; j<s.size(); j++)
            board[i][j]=s.at(j)=='#'?1:0;
    }
    ll cnt=0;
    for (ll i=0, j=0; i<N; i++, j+=3)
        if (board[i][j%31]==1)
            cnt++;
    cout<<cnt;
}

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
    vll nums;
    for (int i=0; i<200; i++) {
        ll x; cin>>x; nums.PB(x);
    }
    for (int i=0; i<nums.size(); i++)
    for (int j=0; j<i; j++)
    for (int k=0; k<j; k++)
    if (nums[i]+nums[j]+nums[k]==2020) {
        cout<<nums[i]*nums[j]*nums[k]<<"\n";
    }
}
